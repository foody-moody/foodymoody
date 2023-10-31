package com.foodymoody.be.comment.repository;

import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

}
