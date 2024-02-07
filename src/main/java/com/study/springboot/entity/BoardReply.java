package com.study.springboot.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BoardReply")
@Entity
public class BoardReply {

    @Id
    @SequenceGenerator (
            name = "boardReplySequence",
            sequenceName = "BoardReply_SEQ",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "boardReplySequence")
    private Long rno;
    private String content;
    private ZonedDateTime regDate; 
    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id")
    private Member id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_bno") 
    private Board board;
}