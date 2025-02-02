package tamtam.mooney.domain.entity;

import jakarta.persistence.*;
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
public class UserSchedule extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long scheduleId;

    @Column
    private String title;

    @Enumerated(EnumType.STRING)
    @Column
    private CategoryName categoryName;

    @Column
    private LocalDateTime startDateTime;

    @Column
    private LocalDateTime endDateTime;

    @Column
    private String location;

    @Column(nullable = false)
    private Boolean isRepeating; // 반복 여부

    @Column
    private String repeatType; // 반복 주기 (예: DAILY, WEEKLY, MONTHLY 등)

    @Column
    private DayOfWeek repeatDayOfWeek;

    @Column
    private Integer repeatDayOfMonth;

    @Column
    private BigDecimal predictedAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public UserSchedule(String title, CategoryName categoryName, LocalDateTime startDateTime, LocalDateTime endDateTime,
                        String location, Boolean isRepeating, String repeatType, DayOfWeek repeatDayOfWeek,
                        Integer repeatDayOfMonth, User user) {
        this.title = title;
        this.categoryName = categoryName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        // 기본값 처리
        this.isRepeating = (isRepeating != null) ? isRepeating : false;
        this.repeatType = repeatType;
        this.repeatDayOfWeek = repeatDayOfWeek;
        this.repeatDayOfMonth = repeatDayOfMonth;
        this.user = user;
    }

    public void setCategoryNameAndPredictedAmount(CategoryName categoryName, BigDecimal predictedAmount) {
        if (categoryName != null) {
            this.categoryName = categoryName;
        }
        if (predictedAmount != null) {
            this.predictedAmount = predictedAmount;
        }
    }
}