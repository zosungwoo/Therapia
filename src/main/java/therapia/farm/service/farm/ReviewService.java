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
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FarmRepository farmRepository;

    // 리뷰 등록
    public Long createReview(Review review){
        reviewRepository.save(review);
        farmRepository.findById(review.getFarm().getId()).get().updateReviewRating();  // 농장의 Rating 없데이트!
        return review.getId();
    }

    // 리뷰 업데이트 (변경 감지 이용)
    public void updateReview(Long reviewId, String title, String contents, Double rating){
        Review review = reviewRepository.findById(reviewId).get();
        review.setTitle(title);
        review.setContents(contents);
        review.setRating(rating);
    }

    // 리뷰 삭제
    public void removeReview(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }

    // 모든 리뷰 조회
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }

    // 단건 조회
    public Review findOne(Long reviewId){
        return reviewRepository.findById(reviewId).get();
    }

    // 농장 이름으로 조회
    public List<Review> findByFarmName(String name){
        Farm farm = farmRepository.findByName(name);
        return farm.getReviews();
    }


}
