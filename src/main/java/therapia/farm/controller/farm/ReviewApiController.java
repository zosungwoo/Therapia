package therapia.farm.controller.farm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import therapia.farm.dto.farm.ReviewRequestDto;
import therapia.farm.dto.farm.ReviewResponseDto;
import therapia.farm.service.farm.ReviewService;

@Api(tags = {"Review API"})
@Log4j2
@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;
    @ApiOperation(value = "리뷰 생성",
            notes = "개별 리뷰 생성")
    @PostMapping("/api/members/{memberid}/farms/{farmid}/reviews")
    public ResponseEntity<?> addReview(@PathVariable("memberid")Long memberId,
                                       @PathVariable("farmid") Long farmId,
                                       @RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto reviewResponseDto = reviewService.createReview(requestDto, memberId, farmId);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "리뷰 업데이트",
            notes = "개별 리뷰 업데이트")
    @PutMapping("/api/review/update/{memberid}/{reviewid}")
    public ResponseEntity<?> updateReview(@PathVariable("memberid")Long memberId, @PathVariable("reviewid")Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto reviewResponseDto = reviewService.updateReview(requestDto, reviewId, memberId);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "리뷰 삭제",
            notes = "개별 리뷰 삭제")
    @DeleteMapping("/api/review/remove/{reviewid}")
    public void removeReview(@PathVariable("reviewid")Long reviewId) {
        reviewService.removeReview(reviewId);
    }

    @ApiOperation(value = "리뷰 가져오기",
            notes = "리뷰 ID로 리뷰 가져오기")
    @GetMapping("/api/reviews/{reviewid}")
    public ResponseEntity<?> reviewByReviewId (@PathVariable("reviewid")Long reviewId) {
        return new ResponseEntity<>(reviewService.findOne(reviewId), HttpStatus.OK);
    }

    @ApiOperation(value = "모든 리뷰 가져오기",
            notes = "모든 리뷰 List 가져오기")
    @GetMapping("/api/reviews")
    public ResponseEntity<?> findAllReviews() {
        return new ResponseEntity<>(reviewService.findReviews(), HttpStatus.OK);
    }

    @ApiOperation(value = "농장의 리뷰 가져오기",
            notes = "농장 ID로 리뷰 List 가져오기")
    @GetMapping("/api/farms/{farmid}/reviews")
    public ResponseEntity<?> reviewByFarmId (@PathVariable("farmid")Long farmId) {
        return new ResponseEntity<>(reviewService.findByFarmId(farmId), HttpStatus.OK);
    }
}
