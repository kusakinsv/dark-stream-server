package ru.dark.stream.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicRequest {
    String trackName;
    String author;
    String url;
    String duration;
    String pattern;

    @Override
    public String toString() {
        return String.format(
                "-----------------------\n" +
                        " %s, %s, %s \n %s", this.trackName, this.author, this.duration, this.url);
    }

}
