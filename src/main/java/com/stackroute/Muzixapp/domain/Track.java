package com.stackroute.Muzixapp.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "track")
public class Track
{
    @Id
    int trackId;
    // @JsonProperty("name")
    String trackName;
    String trackComments;

//  @GeneratedValue //(strategy = GenerationType.AUTO)
}
