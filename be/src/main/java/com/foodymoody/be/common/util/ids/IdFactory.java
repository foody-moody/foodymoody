package com.foodymoody.be.common.util.ids;

import com.foodymoody.be.common.util.IdGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class IdFactory {

    private IdFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static MemberId createMemberId(String id) {
        return createId(MemberId.class, id);
    }

    public static MemberId createMemberId() {
        return createId(MemberId.class);
    }

    public static StoreId createStoreId(String id) {
        return createId(StoreId.class, id);
    }

    public static StoreId createStoreId() {
        return createId(StoreId.class);
    }

    public static FeedId createFeedId(String id) {
        return createId(FeedId.class, id);
    }

    public static FeedId createFeedId() {
        return createId(FeedId.class);
    }

    public static ImageMenuId createImageMenuId(String id) {
        return createId(ImageMenuId.class, id);
    }

    public static ImageMenuId createImageMenuId() {
        return createId(ImageMenuId.class);
    }

    public static StoreMoodId createStoreMoodId(String id) {
        return createId(StoreMoodId.class, id);
    }

    public static StoreMoodId createStoreMoodId() {
        return createId(StoreMoodId.class);
    }

    public static FeedLikeId createFeedHeartId(String id) {
        return createId(FeedLikeId.class, id);
    }

    public static FeedLikeId createFeedHeartId() {
        return createId(FeedLikeId.class);
    }

    public static FeedLikeCountId createFeedLikeCountId(String id) {
        return createId(FeedLikeCountId.class, id);
    }

    public static FeedLikeCountId createFeedLikeCountId() {
        return createId(FeedLikeCountId.class);
    }

    public static FeedCommentLikeCountId createFeedCommentLikeCountId(String id) {
        return createId(FeedCommentLikeCountId.class, id);
    }

    public static FeedCommentLikeCountId createFeedCommentLikeCountId() {
        return createId(FeedCommentLikeCountId.class);
    }

    public static ImageId createImageId(String id) {
        return createId(ImageId.class, id);
    }

    public static ImageId createImageId() {
        return createId(ImageId.class);
    }

    public static MenuId createMenuId(String id) {
        return createId(MenuId.class, id);
    }

    public static MenuId createMenuId() {
        return createId(MenuId.class);
    }

    public static FeedCommentId createFeedCommentId(String id) {
        return createId(FeedCommentId.class, id);
    }

    public static FeedCommentId createFeedCommentId() {
        return createId(FeedCommentId.class);
    }

    public static TasteMoodId createTasteMoodId(String id) {
        return createId(TasteMoodId.class, id);
    }

    public static TasteMoodId createTasteMoodId() {
        return createId(TasteMoodId.class);
    }

    public static FeedCollectionId createFeedCollectionId(String id) {
        return createId(FeedCollectionId.class, id);
    }

    public static FeedCollectionId createFeedCollectionId() {
        return createId(FeedCollectionId.class);
    }

    public static FeedCollectionCommentId createFeedCollectionCommentId(String id) {
        return createId(FeedCollectionCommentId.class, id);
    }

    public static FeedCollectionCommentId createFeedCollectionCommentId() {
        return createId(FeedCollectionCommentId.class);
    }

    public static FeedReplyId createFeedReplyId(String id) {
        return createId(FeedReplyId.class, id);
    }

    public static FeedReplyId createFeedReplyId() {
        return createId(FeedReplyId.class);
    }

    public static FeedReplyLikeCountId createFeedReplyLikeCountId(String id) {
        return createId(FeedReplyLikeCountId.class, id);
    }

    public static FeedReplyLikeCountId createFeedReplyLikeCountId() {
        return createId(FeedReplyLikeCountId.class);
    }

    public static NotificationId createNotificationId(String notificationId) {
        return createId(NotificationId.class, notificationId);
    }

    public static NotificationId createNotificationId() {
        return createId(NotificationId.class);
    }

    public static NotificationSettingId createNotificationSettingId(String id) {
        return createId(NotificationSettingId.class, id);
    }

    public static NotificationSettingId createNotificationSettingId() {
        return createId(NotificationSettingId.class);
    }

    public static FeedCollectionReplyId createFeedCollectionReplyId() {
        return createId(FeedCollectionReplyId.class);
    }

    public static FeedCollectionReplyId createFeedCollectionReplyId(String id) {
        return createId(FeedCollectionReplyId.class, id);
    }

    public static FeedCollectionCommentLikeId createFeedCollectionCommentLikeId() {
        return createId(FeedCollectionCommentLikeId.class);
    }

    public static FeedCollectionCommentLikeId createFeedCollectionCommentLikeId(String id) {
        return createId(FeedCollectionCommentLikeId.class, id);
    }

    public static FeedCollectionLikeId createFeedCollectionLikeId() {
        return createId(FeedCollectionLikeId.class);
    }

    public static FeedCollectionLikeId createFeedCollectionLikeId(String id) {
        return createId(FeedCollectionLikeId.class, id);
    }

    public static FeedCollectionReplyLikeId createFeedCollectionReplyLikeId() {
        return createId(FeedCollectionReplyLikeId.class);
    }

    public static FeedCollectionReplyLikeId createFeedCollectionReplyLikeId(String id) {
        return createId(FeedCollectionReplyLikeId.class, id);
    }

    public static FeedCollectionLikeCountId createFeedCollectionLikeCountId() {
        return createId(FeedCollectionLikeCountId.class);
    }

    public static FeedCollectionLikeCountId createFeedCollectionLikeCountId(String id) {
        return createId(FeedCollectionLikeCountId.class, id);
    }

    public static FeedCollectionCommentLikeCountId createFeedCollectionCommentLikeCountId() {
        return createId(FeedCollectionCommentLikeCountId.class);
    }

    public static FeedCollectionCommentLikeCountId createFeedCollectionCommentLikeCountId(String id) {
        return createId(FeedCollectionCommentLikeCountId.class, id);
    }

    public static FeedCollectionReplyLikeCountId createFeedCollectionReplyLikeCountId() {
        return createId(FeedCollectionReplyLikeCountId.class);
    }

    public static FeedCollectionReplyLikeCountId createFeedCollectionReplyLikeCountId(String id) {
        return createId(FeedCollectionReplyLikeCountId.class, id);
    }

    public static FeedCollectionMoodId createFeedCollectionMoodId() {
        return createId(FeedCollectionMoodId.class);
    }

    public static FeedCollectionMoodId createFeedCollectionMoodId(String id) {
        return createId(FeedCollectionMoodId.class, id);
    }

    public static FeedCollectionMoodsId createFeedCollectionMoodsId() {
        return createId(FeedCollectionMoodsId.class);
    }

    public static FeedCollectionMoodsId createFeedCollectionMoodsId(String id) {
        return createId(FeedCollectionMoodsId.class, id);
    }

    public static FeedReplyLikeId createFeedReplyLikeId() {
        return createId(FeedReplyLikeId.class);
    }

    public static FeedReplyLikeId createFeedReplyLikeId(String id) {
        return createId(FeedReplyLikeId.class, id);
    }

    public static FeedCommentLikeId createFeedCommentLikeId() {
        return createId(FeedCommentLikeId.class);
    }

    public static FeedCommentLikeId createFeedCommentLikeId(String id) {
        return createId(FeedCommentLikeId.class, id);
    }

    public static StoreLikeId createStoreLikeId() {
        return createId(StoreLikeId.class);
    }

    public static StoreLikeCountId createStoreLikeCountId() {
        return createId(StoreLikeCountId.class);
    }

    private static <T extends BaseId> T createId(Class<T> idClass, String id) {
        try {
            Constructor<T> constructor = idClass.getConstructor(String.class);
            return constructor.newInstance(id);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new IllegalArgumentException("Id 클래스 생성에 실패했습니다.");
        }
    }

    private static <T extends BaseId> T createId(Class<T> idClass) {
        try {
            Constructor<T> constructor = idClass.getConstructor(String.class);
            return constructor.newInstance(IdGenerator.generate());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new IllegalArgumentException("Id 클래스 생성에 실패했습니다.");
        }
    }

}
