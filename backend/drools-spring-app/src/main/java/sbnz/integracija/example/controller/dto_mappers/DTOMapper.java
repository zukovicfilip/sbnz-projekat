package sbnz.integracija.example.controller.dto_mappers;

import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.model.persistance.Property;

public class DTOMapper {

    public static PropertyDTO getPropertyDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setBuildDate(property.getBuildDate());
        propertyDTO.setCoordinates(property.getCoordinates());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setOwner(property.getOwner());
        propertyDTO.setSurface(property.getSurface());
        propertyDTO.setPricePerSquareM(property.getPricePerSquareM());
        propertyDTO.setNumberOfRooms(property.getNumberOfRooms());
        return propertyDTO;
    }

}
