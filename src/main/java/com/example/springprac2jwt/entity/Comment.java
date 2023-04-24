package com.example.springprac2jwt.entity;

import com.example.springprac2jwt.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;    //pk

    @Column(nullable = false)
    private String comment;

    @JsonBackReference
    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;  //fk


    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;  //fk


    @Column(nullable = false)
    @ColumnDefault("0")
    private int likeCount;

    public Comment(User user, Post post, CommentRequestDto commentRequestDto) {
        this.user = user;
        this.post = post;
        this.comment = commentRequestDto.getComment();
    }

    public void setUser(User user){
        this.user = user;
    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}
