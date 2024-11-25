package tamtam.mooney.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tamtam.mooney.global.common.BaseTimeEntity;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Schedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private BigDecimal predictedAmount;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    @Column
    private String title;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    private String location;

    @Column(nullable = false)
    private Boolean isRepeating; // 반복 여부

    @Enumerated(EnumType.STRING)
    private String repeatType; // 반복 주기 (예: DAILY, WEEKLY, MONTHLY 등)

    @Column
    private DayOfWeek repeatDayOfWeek;

    @Column
    private Integer repeatDayOfMonth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public Schedule(BigDecimal predictedAmount, ExpenseCategory expenseCategory, String title,
                    LocalDateTime startDateTime, LocalDateTime endDateTime, String location,
                    Boolean isRepeating, String repeatType, DayOfWeek repeatDayOfWeek, Integer repeatDayOfMonth, User user) {
        this.predictedAmount = predictedAmount;
        this.expenseCategory = expenseCategory;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        // 기본값 처리
        this.isRepeating = (isRepeating != null) ? isRepeating : false;
        this.repeatType = (repeatType != null) ? repeatType : null;
        this.repeatDayOfWeek = (repeatDayOfWeek != null) ? repeatDayOfWeek : null;
        this.repeatDayOfMonth = (repeatDayOfMonth != null) ? repeatDayOfMonth : null;
        this.user = user;
    }
}