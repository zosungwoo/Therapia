package therapia.farm.controller.farm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import therapia.farm.service.farm.MemberService;
import therapia.farm.service.farm.ReviewService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {
    private final ReviewService reviewService;
    private final MemberService memberService;

    @PostMapping("/api/review/add/{memberid}/{farmid}")
    public Map<String, String> addReview(@PathVariable("memberid")Long member_Id, @PathVariable("farmid") Long farm_Id, @RequestBody Map<String,String> map) {
        String rating = map.get("rating");
        String title = map.get("title");
        String contents = map.get("contents");
        String nickname = memberService.findNicknameById(member_Id);
        Long reviewId = reviewService.createReview(nickname, member_Id, farm_Id, title, contents, Double.valueOf(rating));
        Map<String, String> map1 = new HashMap<>();
        map1.put("reviewid", reviewId.toString());
        System.out.println(map1);
        return map1;
    }

    @PostMapping("/api/review/update/{memberid}/{reviewid}")
    public void updateReview(@PathVariable("memberid")Long member_Id, @PathVariable("memberid")Long review_Id, @RequestBody Map<String,String> map){
        String rating = map.get("rating");
        String title = map.get("title");
        String contents = map.get("contents");
        reviewService.updateReview(review_Id, title, contents, Double.valueOf(rating));
    }

    @PostMapping("/api/review/remove/{reviewid}")
    public void removeReview(@PathVariable("reviewid")Long review_Id){
        reviewService.removeReview(review_Id);
    }

    @GetMapping("/api/review/findall")
    public Result findAllReviews() {
        return new Result(reviewService.findReviews());
    }

    @GetMapping("/api/review/findone/{reviewid}")
    public Result findReview (@PathVariable("reviewid")Long review_Id) {
        return new Result(reviewService.findOne(review_Id));
    }

    @GetMapping("/api/review/findname/{farmid}")
    public Result findReviewByFarmid (@PathVariable("farmid")Long farm_Id) {
        return new Result(reviewService.findByFarmId(farm_Id));
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
