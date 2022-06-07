package therapia.farm.service.farm;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import therapia.farm.domain.farm.Farm;
import therapia.farm.domain.farm.Member;
import therapia.farm.domain.farm.Review;
import therapia.farm.repository.farm.ReviewRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static therapia.farm.domain.farm.FarmCategory.EXP;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    FarmService farmService;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 리뷰등록() throws Exception {
        //given
        Member member = new Member();
        String nickname1 = "정지연";
        String email1 = "wjdwldus2912@gmail.com";
        Long memberId = memberService.createMember(nickname1,email1);

        Farm farm  = new Farm();
        farm.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm.setCategory(EXP);
        farm.setLocation_x(126.8002577);
        farm.setLocation_y(36.7107201);
        farm.setPhone("041-334-5447");
        farm.setName("가나안농장");
        farm.setPlaceUrl("http://www.cnafarm.com");
        Long farmId = farmService.createFarm(farm);

        Review review1 = new Review();
        review1.setMember(member);
        review1.setFarm(farm);
        review1.setTitle("가나안농장 리뷰");
        review1.setRating(4.0);
        review1.setContents("농장 좋은데 약간 아쉬움용");
        review1.setNickname(member.getNickname());

        Review review2 = new Review();
        review2.setMember(member);
        review2.setFarm(farm);
        review2.setTitle("가나안농장 리뷰");
        review2.setRating(3.5);
        review2.setContents("농장 좋은데 약간 아쉬움용");
        review2.setNickname(member.getNickname());

        Review review3 = new Review();
        review3.setMember(member);
        review3.setFarm(farm);
        review3.setTitle("가나안농장 리뷰");
        review3.setRating(3.3);
        review3.setContents("농장 좋은데 약간 아쉬움용");
        review3.setNickname(member.getNickname());

        //when
        Long reviewId1 = reviewService.createReview(review1);
        Long reviewId2 = reviewService.createReview(review2);
        Long reviewId3 = reviewService.createReview(review3);

        //then
        assertEquals(review1, reviewRepository.getById(reviewId1));
        assertEquals(review2, reviewRepository.getById(reviewId2));
        assertEquals(review3, reviewRepository.getById(reviewId3));
    }

    @Test
    @Rollback(false)
    public void 리뷰_변경() throws Exception {
        //given
        Member member = new Member();
        String nickname1 = "정지연";
        String email1 = "wjdwldus2912@gmail.com";
        Long memberId = memberService.createMember(nickname1,email1);

        Farm farm  = new Farm();
        farm.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm.setCategory(EXP);
        farm.setLocation_x(126.8002577);
        farm.setLocation_y(36.7107201);
        farm.setPhone("041-334-5447");
        farm.setName("가나안농장");
        farm.setPlaceUrl("http://www.cnafarm.com");
        Long farmId = farmService.createFarm(farm);

        Review review1 = new Review();
        review1.setMember(member);
        review1.setFarm(farm);
        review1.setTitle("가나안농장 리뷰");
        review1.setRating(4.0);
        review1.setContents("농장 좋은데 약간 아쉬움용");
        review1.setNickname(member.getNickname());

        Review review2 = new Review();
        review2.setMember(member);
        review2.setFarm(farm);
        review2.setTitle("가나안농장 리뷰");
        review2.setRating(3.5);
        review2.setContents("농장 좋은데 약간 아쉬움용");
        review2.setNickname(member.getNickname());

        Long reviewId1 = reviewService.createReview(review1);
        Long reviewId2 = reviewService.createReview(review2);


        //when
        Double changeRating = 3.2;
        reviewService.updateReview(reviewId2, "가나안농장 리뷰", "농장 좋은데 흠", changeRating);

        //then
        assertEquals(changeRating, review2.getRating());
    }

    @Test
    @Rollback(false)
    public void 리뷰삭제() throws Exception {
        //given
        Member member = new Member();
        String nickname1 = "정지연";
        String email1 = "wjdwldus2912@gmail.com";
        Long memberId = memberService.createMember(nickname1,email1);

        Farm farm  = new Farm();
        farm.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm.setCategory(EXP);
        farm.setLocation_x(126.8002577);
        farm.setLocation_y(36.7107201);
        farm.setPhone("041-334-5447");
        farm.setName("가나안농장");
        farm.setPlaceUrl("http://www.cnafarm.com");
        Long farmId = farmService.createFarm(farm);

        Review review1 = new Review();
        review1.setMember(member);
        review1.setFarm(farm);
        review1.setTitle("가나안농장 리뷰");
        review1.setRating(4.0);
        review1.setContents("농장 좋은데 약간 아쉬움용");
        review1.setNickname(member.getNickname());

        Review review2 = new Review();
        review2.setMember(member);
        review2.setFarm(farm);
        review2.setTitle("가나안농장 리뷰");
        review2.setRating(3.5);
        review2.setContents("농장 좋은데 약간 아쉬움용");
        review2.setNickname(member.getNickname());

        Long reviewId1 = reviewService.createReview(review1);
        Long reviewId2 = reviewService.createReview(review2);
        
        //when
        reviewService.removeReview(reviewId2);
        List<Review> result = reviewService.findReviews();

        //then
        assertEquals(result.size(), 1);
    }

    @Test
    @Rollback(false)
    public void 농장이름으로_조회() throws Exception {
        //given
        Member member = new Member();
        String nickname1 = "정지연";
        String email1 = "wjdwldus2912@gmail.com";
        Long memberId = memberService.createMember(nickname1,email1);

        Farm farm1 = new Farm();
        farm1.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm1.setCategory(EXP);
        farm1.setLocation_x(126.8002577);
        farm1.setLocation_y(36.7107201);
        farm1.setPhone("041-334-5447");
        farm1.setName("가나안농장");
        farm1.setPlaceUrl("http://www.cnafarm.com");
        Long farmId1 = farmService.createFarm(farm1);

        Farm farm2 = new Farm();
        farm2.setAddress("충청남도 예산군 오가면 오촌리 318");
        farm2.setCategory(EXP);
        farm2.setLocation_x(125.8002577);
        farm2.setLocation_y(34.7107201);
        farm2.setPhone("041-334-5447");
        farm2.setName("고냥농장");
        farm2.setPlaceUrl("http://www.cnafarm.com");
        Long farmId2 = farmService.createFarm(farm2);

        Review review1 = new Review();
        review1.setMember(member);
        review1.setFarm(farm1);
        review1.setTitle("가나안농장 리뷰1");
        review1.setRating(4.0);
        review1.setContents("농장 좋은데 약간 아쉬움용");
        review1.setNickname(member.getNickname());

        Review review2 = new Review();
        review2.setMember(member);
        review2.setFarm(farm1);
        review2.setTitle("가나안농장 리뷰2");
        review2.setRating(5.0);
        review2.setContents("굿 좋아염");
        review2.setNickname(member.getNickname());

        Review review3 = new Review();
        review3.setMember(member);
        review3.setFarm(farm2);
        review3.setTitle("고냥농장 리뷰");
        review3.setRating(5.0);
        review3.setContents("굿 좋아염");
        review3.setNickname(member.getNickname());

        Long reviewId1 = reviewService.createReview(review1);
        Long reviewId2 = reviewService.createReview(review2);
        Long reviewId3 = reviewService.createReview(review3);

        //when
        List<Review> reviewList = reviewService.findByFarmName("가나안농장");

        //then
        System.out.println(reviewList.size());
        assertEquals(reviewList.size(), 2);
    }
}