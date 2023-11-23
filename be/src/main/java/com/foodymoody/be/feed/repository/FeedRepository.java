package com.foodymoody.be.feed.repository;

import com.foodymoody.be.feed.domain.Feed;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedRepository extends JpaRepository<Feed, String> {

//    스칼라 서브쿼리 사용
    @Query(value = "SELECT new com.foodymoody.be.feed.repository.MemberProfileFeedPreviewResponse"
            + "(f.id, (SELECT i.url FROM Image i WHERE i.id = MIN(im.id))) "
            + "FROM Feed f "
            + "LEFT JOIN f.imageMenus.imageMenusList im "
            + "WHERE f.memberId = :memberId "
            + "GROUP BY f "
            + "ORDER BY f.createdAt DESC")
    List<MemberProfileFeedPreviewResponse> readAllPreviewsByMemberId(@Param("memberId") String memberId);

//    네이티브 쿼리, 조인 사용
    @Query(value = "SELECT f.id AS id, i.url AS imageUrl FROM feed f "
            + "LEFT JOIN feed_image_menus_list fil1 ON f.id = fil1.feed_id "
            + "LEFT JOIN image_menu im ON fil1.image_menus_list_id = im.id "
            + "LEFT JOIN image i ON im.image_id = i.id "
            + "LEFT JOIN feed_image_menus_list fil2 ON f.id = fil2.feed_id AND fil2.image_menus_list_id < fil1.image_menus_list_id "
            + "WHERE fil2.feed_id IS NULL AND fil2.image_menus_list_id IS NULL AND f.member_id = :memberId "
            + "ORDER BY f.created_at DESC", nativeQuery = true )
    List<Object[]> readAllPreviewsByMemberId2(@Param("memberId") String memberId);

    List<Feed> findByMemberId(String memberId);
}
