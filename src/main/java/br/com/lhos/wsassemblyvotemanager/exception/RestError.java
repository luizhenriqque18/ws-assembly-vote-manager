package br.com.lhos.wsassemblyvotemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestError {
    private HttpStatus status;
    private String message;
    private List<Map<String, String>> errors;
}
