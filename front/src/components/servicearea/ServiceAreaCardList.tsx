import { ServiceArea } from "../../models/servicearea/ServiceArea";
import CardListStyle from "../shared/CardList/CardList.style";
import ServiceAreaCard from "./ServiceAreaCard";

interface ServiceAreaCardListProps {
    serviceAreas: ServiceArea[]
    toggleAvailability: (serviceArea: ServiceArea) => void
}

const ServiceAreaCardList = ({serviceAreas, toggleAvailability} : ServiceAreaCardListProps) => {

    return (
        <CardListStyle>
            {serviceAreas.map(area => (
                <ServiceAreaCard 
                    serviceArea={area}
                    toggleAvailability={toggleAvailability}
                    key={area.id}
                />
            ))}
        </CardListStyle>
    )

}

export default ServiceAreaCardList;