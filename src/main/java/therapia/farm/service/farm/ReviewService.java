package therapia.farm.service.farm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.Review;
import therapia.farm.repository.farm.FarmRepository;
import therapia.farm.repository.farm.ReviewRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmService farmService;

    @Autowired
    private MemberService memberService;

    //농장 평점 업데이트
    @Transactional
    public void updateReviewRating(Long farmId){
        List<Review> reviewList = reviewRepository.findAllByfarmId(farmId);
        Farm farm = farmService.findById(farmId);
        if (reviewList.size() == 0) {
            farm.setReviewRating(0.0);
        } else {
            Double ratingsum = 0.0;
            for(Review review : reviewList){
                ratingsum += review.getRating();
            }
            Double avgReview = ratingsum / reviewList.size();
            farm.setReviewRating(avgReview);
        }
    }

    // 리뷰 등록
    @Transactional
    public Long createReview(String nickname, Long member_Id, Long farm_Id, String title, String contents, Double rating){
        Review review = new Review();
        review.setNickname(nickname);
        review.setMember(memberService.findMemberById(member_Id));
        review.setFarm(farmService.findById(farm_Id));
        review.setTitle(title);
        review.setContents(contents);
        review.setRating(rating);
        reviewRepository.save(review);

        updateReviewRating(farm_Id);// 농장의 Rating 업데이트!
        return review.getId();
    }

    // 리뷰 업데이트 (변경 감지 이용)
    @Transactional
    public void updateReview(Long reviewId, String title, String contents, Double rating){
        Review review = reviewRepository.findById(reviewId).get();
        review.setTitle(title);
        review.setContents(contents);
        review.setRating(rating);
        Long farmId = review.getFarm().getId();
        updateReviewRating(farmId);// 농장의 Rating 업데이트!
    }

    // 리뷰 삭제
    @Transactional
    public void removeReview(Long reviewId){
        Long farmId = reviewRepository.findById(reviewId).get().getFarm().getId();
        reviewRepository.deleteById(reviewId);
        updateReviewRating(farmId);
    }

    // 모든 리뷰 조회
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }

    // 단건 조회
    public Review findOne(Long reviewId){
        return reviewRepository.findReviewById(reviewId);
    }

    // 농장 이름으로 조회
    public List<Review> findByFarmName(String name){
        Long farmId = farmRepository.findByName(name).getId();
        return reviewRepository.findAllByfarmId(farmId);
    }

    //농장 ID로 조회
    public List<Review> findByFarmId(Long farmId) {
        return reviewRepository.findAllByfarmId(farmId);
    }

}
