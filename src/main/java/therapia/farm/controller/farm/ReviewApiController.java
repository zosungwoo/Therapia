package therapia.farm.controller.farm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import therapia.farm.exception.CustomException;
import therapia.farm.service.farm.MemberService;
import therapia.farm.service.farm.ReviewService;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"Review API"})
@Log4j2
@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @ApiOperation(value = "리뷰 생성",
            notes = "개별 리뷰 생성")
    @PostMapping("/api/members/{memberid}/farms/{farmid}/reviews")
    public Map<String, String> addReview(@PathVariable("memberid")Long member_Id,
                                         @PathVariable("farmid") Long farm_Id,
                                         @RequestBody Map<String,String> map) throws Exception{
        if(memberService.findMemberById(member_Id) == null) {
            throw new CustomException("존재하지 않는 사용자");
        }
        String rating = map.get("rating");
        String title = map.get("title");
        String contents = map.get("contents");
        String nickname = memberService.findNicknameById(member_Id);
        Long reviewId = reviewService.createReview(nickname, member_Id, farm_Id, title, contents, Double.valueOf(rating));
        Map<String, String> map1 = new HashMap<>();
        map1.put("reviewid", reviewId.toString());
        return map1;
    }

    @ApiOperation(value = "리뷰 업데이트",
            notes = "개별 리뷰 업데이트")
    @PutMapping("/api/review/update/{memberid}/{reviewid}")
    public void updateReview(@PathVariable("memberid")Long member_Id, @PathVariable("reviewid")Long review_Id, @RequestBody Map<String,String> map) throws Exception{
        if(memberService.findMemberById(member_Id) == null) {
            throw new CustomException("존재하지 않는 사용자");
        }
        if(reviewService.findOne(review_Id) == null){
            throw new CustomException("존재하지 않는 리뷰");
        }
        String rating = map.get("rating");
        String title = map.get("title");
        String contents = map.get("contents");
        reviewService.updateReview(review_Id, title, contents, Double.valueOf(rating));
    }

    @ApiOperation(value = "리뷰 삭제",
            notes = "개별 리뷰 삭제")
    @DeleteMapping("/api/review/remove/{reviewid}")
    public void removeReview(@PathVariable("reviewid")Long review_Id) throws Exception{
        if(reviewService.findOne(review_Id) == null){
            throw new CustomException("존재하지 않는 리뷰");
        }
        reviewService.removeReview(review_Id);
    }

    @ApiOperation(value = "리뷰 가져오기",
            notes = "리뷰 ID로 리뷰 가져오기")
    @GetMapping("/api/reviews/{reviewid}")
    public Result reviewByReviewId (@PathVariable("reviewid")Long review_Id) {
        return new Result(reviewService.findOne(review_Id));
    }

    @ApiOperation(value = "모든 리뷰 가져오기",
            notes = "모든 리뷰 List 가져오기")
    @GetMapping("/api/reviews")
    public Result findAllReviews() {
        return new Result(reviewService.findReviews());
    }



    @ApiOperation(value = "농장의 리뷰 가져오기",
            notes = "농장 ID로 리뷰 List 가져오기")
    @GetMapping("/api/farms/{farmid}/reviews")
    public Result reviewByFarmId (@PathVariable("farmid")Long farm_Id) {
        return new Result(reviewService.findByFarmId(farm_Id));
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
