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
