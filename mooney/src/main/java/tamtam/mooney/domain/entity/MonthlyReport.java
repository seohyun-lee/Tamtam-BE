package tamtam.mooney.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tamtam.mooney.global.common.BaseTimeEntity;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MonthlyReport extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long monthlyReportId;

    @Column(nullable = false, length = 7)
    private String period;

    @Column(nullable = false)
    @NotNull
    private BigDecimal budgetAmount;

    @Column(nullable = false)
    @NotNull
    private BigDecimal totalExpenseAmount;

    @Column(nullable = false)
    @NotNull
    private BigDecimal totalIncomeAmount;

    @Column
    private String agentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Builder
    public MonthlyReport(String period, BigDecimal budgetAmount, BigDecimal totalExpenseAmount, BigDecimal totalIncomeAmount,
                         String agentComment, User user) {
        this.period = period;
        this.budgetAmount = budgetAmount;
        this.totalExpenseAmount = totalExpenseAmount;
        this.totalIncomeAmount = totalIncomeAmount;
        this.agentComment = agentComment;
        this.user = user;
    }

    public void update(BigDecimal budgetAmount, BigDecimal totalExpenseAmount,
                       BigDecimal totalIncomeAmount, String agentComment) {
        if (budgetAmount != null) {
            this.budgetAmount = budgetAmount;
        }
        if (totalExpenseAmount != null) {
            this.totalExpenseAmount = totalExpenseAmount;
        }
        if (totalIncomeAmount != null) {
            this.totalIncomeAmount = totalIncomeAmount;
        }
        if (agentComment != null && !agentComment.isEmpty()) {
            this.agentComment = agentComment;
        }
    }
}