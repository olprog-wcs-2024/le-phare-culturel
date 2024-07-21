package fr.olprog_c.le_phare_culturel.dtos;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;

public record AuthRegisterPostDTO(
    @Email @NotNull @Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$") String email,

    @NotNull @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,64}$") String password,

    @JsonProperty("confirm_password") @NotNull @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,64}$") String confirmPassword,

    @JsonProperty("profile_nickname") @NotNull @Pattern(regexp = "^\\p{L}[\\p{L}\\d]{2,31}$", flags = Flag.UNICODE_CASE) String profileNickname,

    @JsonProperty("profile_description") @NotNull @Length(min = 10, max = 320) String profileDescription,

    @NotNull @Pattern(regexp = "^\\p{L}[\\p{L}\\s\\-]{1,55}$", flags = Flag.UNICODE_CASE) String firstname,

    @NotNull @Pattern(regexp = "^\\p{L}[\\p{L}\\s\\-]{1,55}$", flags = Flag.UNICODE_CASE) String lastname

) {
}
