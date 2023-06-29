package therapia.farm.service.farm;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.Member;
import therapia.farm.domain.farm.Review;
import therapia.farm.dto.farm.ReviewRequestDto;
import therapia.farm.dto.farm.ReviewResponseDto;
import therapia.farm.exception.CustomException;
import therapia.farm.repository.farm.FarmRepository;
import therapia.farm.repository.farm.MemberRepository;
import therapia.farm.repository.farm.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FarmRepository farmRepository;
    private final MemberRepository memberRepository;

    //농장 평점 업데이트
    @Transactional
    public void updateReviewRating(Long farmId){
        List<Review> reviewList = reviewRepository.findAllByFarmId(farmId);
        Farm farm = farmRepository.findById(farmId).get();
        if (reviewList.size() == 0) {
            farm.updateReviewRating(0.0);
        } else {
            Double ratingsum = 0.0;
            for(Review review : reviewList){
                ratingsum += review.getRating();
            }
            Double avgReview = ratingsum / reviewList.size();
            farm.updateReviewRating(avgReview);
        }
    }

    // 리뷰 등록
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto, Long memberId, Long farmId){
        if (memberRepository.findMemberById(memberId).isEmpty()) {
            throw new CustomException("존재하지 않는 사용자");
        }
        if (farmRepository.findById(farmId).isEmpty()) {
            throw new CustomException("존재하지 않는 농장");
        }
        Member member = memberRepository.getById(memberId);
        Farm farm = farmRepository.getById(farmId);
        Review review = reviewRepository.save(ReviewRequestDto.toEntity(requestDto, member, farm));
        updateReviewRating(farmId);// 농장의 Rating 업데이트!
        return new ReviewResponseDto(review);
    }

    // 리뷰 업데이트 (변경 감지 이용)
    @Transactional
    public ReviewResponseDto updateReview(ReviewRequestDto requestDto, Long reviewId, Long memberId){
        if (reviewRepository.findById(reviewId).isEmpty()) {
            throw new CustomException("존재하지 않는 리뷰");
        }
        if (memberRepository.findMemberById(memberId).isEmpty()) {
            throw new CustomException("존재하지 않는 사용자");
        }
        Review review = reviewRepository.findReviewById(reviewId);
        if (requestDto.getTitle() != null) review.updateTitle(requestDto.getTitle());
        if (requestDto.getContents() != null) review.updateContents(requestDto.getContents());
        if (requestDto.getRating() != null) {
            review.updateRating(requestDto.getRating());
            updateReviewRating(review.getFarm().getId());
        }
        return new ReviewResponseDto(review);
    }

    // 리뷰 삭제
    @Transactional
    public void removeReview(Long reviewId){
        if (reviewRepository.findById(reviewId).isEmpty()) {
            throw new CustomException("존재하지 않는 리뷰");
        }
        Long farmId = reviewRepository.findById(reviewId).get().getFarm().getId();
        reviewRepository.deleteById(reviewId);
        updateReviewRating(farmId);
    }

    // 모든 리뷰 조회
    public List<ReviewResponseDto> findReviews(){
        return reviewRepository.findAll().stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

    // 단건 조회
    public ReviewResponseDto findOne(Long reviewId){
        if (reviewRepository.findById(reviewId).isEmpty()) {
            throw new CustomException("존재하지 않는 리뷰");
        }
        return new ReviewResponseDto(reviewRepository.findReviewById(reviewId));
    }

    //농장 ID로 조회
    public List<ReviewResponseDto> findByFarmId(Long farmId) {
        if (farmRepository.findById(farmId).isEmpty()) {
            throw new CustomException("존재하지 않는 농장");
        }
        List<Review> reviewList = reviewRepository.findAllByFarmId(farmId);
        return reviewList.stream().map(ReviewResponseDto::new).collect(Collectors.toList());
    }

}
