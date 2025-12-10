package apiui.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResp {

        private String userId;
        private String username;
        private String password;
        private String token;
        private String expires;

        @JsonProperty("created_date")
        private String createdDate; // Теперь можно использовать camelCase

        @JsonProperty("isActive")
        private boolean isActive;
}
