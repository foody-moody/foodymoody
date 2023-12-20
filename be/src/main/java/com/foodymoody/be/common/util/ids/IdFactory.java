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

    public static FeedId createFeedId(String id) {
        return createId(FeedId.class, id);
    }

    public static FeedId createFeedId() {
        return createId(FeedId.class);
    }

    public static StoreMoodId createStoreMoodId(String id) {
        return createId(StoreMoodId.class, id);
    }

    public static StoreMoodId createStoreMoodId() {
        return createId(StoreMoodId.class);
    }

    public static FeedHeartId createFeedHeartId(String id) {
        return createId(FeedHeartId.class, id);
    }

    public static FeedHeartId createFeedHeartId() {
        return createId(FeedHeartId.class);
    }

    public static FeedHeartCountId createFeedHeartCountId(String id) {
        return createId(FeedHeartCountId.class, id);
    }

    public static FeedHeartCountId createFeedHeartCountId() {
        return createId(FeedHeartCountId.class);
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

    public static CommentId createCommentId(String id) {
        return createId(CommentId.class, id);
    }

    public static CommentId createCommentId() {
        return createId(CommentId.class);
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

    public static ReplyId createReplyId(String id) {
        return createId(ReplyId.class, id);
    }

    public static ReplyId createReplyId() {
        return createId(ReplyId.class);
    }

    public static NotificationId createNotificationId(String notificationId) {
        return createId(NotificationId.class, notificationId);
    }

    public static NotificationId createNotificationId() {
        return createId(NotificationId.class);
    }

    public static FeedCollectionReplyId createFeedCollectionReplyId() {
        return createId(FeedCollectionReplyId.class);
    }

    public static FeedCollectionReplyId createFeedCollectionReplyId(String id) {
        return createId(FeedCollectionReplyId.class, id);
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
