package com.example.project.DTO;

import lombok.Data;

public record EmailDetails(
        String fromEmail,

        String toEmail,

        String subject,

        String body
) {


}
