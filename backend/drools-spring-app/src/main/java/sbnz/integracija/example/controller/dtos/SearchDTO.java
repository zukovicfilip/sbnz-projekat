package sbnz.integracija.example.controller.dtos;

public class SearchDTO {

    private FilterDTO filterDTO;
    private ScoreDTO scoreDTO;

    public SearchDTO(FilterDTO filterDTO, ScoreDTO scoreDTO) {
        this.filterDTO = filterDTO;
        this.scoreDTO = scoreDTO;
    }

    public SearchDTO() {
    }

    public FilterDTO getFilterDTO() {
        return filterDTO;
    }

    public void setFilterDTO(FilterDTO filterDTO) {
        this.filterDTO = filterDTO;
    }

    public ScoreDTO getScoreDTO() {
        return scoreDTO;
    }

    public void setScoreDTO(ScoreDTO scoreDTO) {
        this.scoreDTO = scoreDTO;
    }
}
