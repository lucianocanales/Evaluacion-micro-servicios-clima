package com.evaluation.locations.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    @JsonProperty("Version")
    private int version;
    @JsonProperty("Key")
    private String key;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Rank")
    private int rank;
    @JsonProperty("LocalizedName")
    private String localizedName;
    @JsonProperty("EnglishName")
    private String englishName;
    @JsonProperty("PrimaryPostalCode")
    private String primaryPostalCode;
    @JsonProperty("Region")
    private RegionDTO region;
    @JsonProperty("Country")
    private CountryDTO country;
    @JsonProperty("AdministrativeArea")
    private AdministrativeAreaDTO administrativeArea;
    @JsonProperty("TimeZone")
    private TimeZoneDTO timeZone;
    @JsonProperty("GeoPosition")
    private GeoPositionDTO geoPosition;
    @JsonProperty("IsAlias")
    private boolean isAlias;
    @JsonProperty("SupplementalAdminAreas")
    private List<SupplementalAdminAreasDTO> supplementalAdminAreas;
    @JsonProperty("DataSets")
    private String[] dataSets;


    @Override
    public String toString() {
        return "LocationDTO [version=" + version + ", key=" + key + ", type=" + type + ", rank=" + rank
                + ", localizedName=" + localizedName + ", englishName=" + englishName + ", primaryPostalCode="
                + primaryPostalCode + "]";
    }
    
}
