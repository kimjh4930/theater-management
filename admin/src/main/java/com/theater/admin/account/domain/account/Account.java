package com.theater.admin.account.domain.account;

import com.theater.admin.common.Name;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@EqualsAndHashCode(of = "id")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email", unique = true))
    private Email email;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "name"))
    private Name name;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "nickname", unique = true))
    private Name nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_role")
    private AccountRole role;

    //우선 평문으로 진행
    @Column
    private String password;

    @CreatedDate
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    /* 추후 작성할 내용 */
//    // MANAGER 권한 요청
//    private boolean requestAuthorization;
//
//    // 권한 요청 시간
//    private LocalDateTime requestAuthorizaionDate;
//
//    // 권한 부여 완료
//    private boolean authorized;
//
//    // 권한 부여 시간
//    private boolean authorizedDate;
//
//    // 권한을 부여한 사람
//    private Long authorizedBy;

    protected Account () {}

    public final void changeRoleTo (AccountRole role){
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public String getName() {
        return name.getName();
    }

    public String getNickname() {
        return nickname.getName();
    }

    public AccountRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    private Account (Builder builder){
        this.id = builder.id;
        this.email = builder.email;
        this.name = builder.name;
        this.nickname = builder.nickname;
        this.password = builder.password;
        this.role = builder.role != null
                ? builder.role
                : AccountRole.UNAUTHORIZED;
    }

    public static class Builder {
        private Long id;
        private AccountRole role;
        private final Email email;
        private final Name name;
        private final Name nickname;
        private final String password;

        public Builder(String email, String name, String nickname, String password) {
            this.name = new Name(name);
            this.email = new Email(email);
            this.nickname = new Name(nickname);
            this.password = password;
        }

        public Builder id (Long id){
            this.id = id;
            return this;
        }

        public Builder role (AccountRole role){
            this.role = role;
            return this;
        }

        public Account build (){
            return new Account(this);
        }
    }
}
