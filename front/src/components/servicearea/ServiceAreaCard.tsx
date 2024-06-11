import { ServiceArea } from "../../models/servicearea/ServiceArea";
import { formatDateTime } from "../../utils/formats/DateTimeFormat";
import Button from "../shared/Button/Button";
import { CardStyle, CardContent } from "../shared/Card/Card.style";
import { RightAlignDiv } from "../shared/style/DivStyle";

interface ServiceAreaCardProps {
    serviceArea: ServiceArea
    toggleAvailability: (serviceArea: ServiceArea) => void;
}

const ServiceAreaCard = ({serviceArea, toggleAvailability} : ServiceAreaCardProps) => {

    // function toggleAvailability() {
    //     ServiceAreaService.toggleAvailability(serviceArea.id, !serviceArea.availableFlag)
    // }

    return <CardStyle>
                <h3>Sevice Area {serviceArea.id}</h3>
                <CardContent>
                    <p>Active: {serviceArea.activeFlag ? "✅" : "❌"}</p>
                    <p>Available: {serviceArea.availableFlag ? "✅" : "❌"}</p>
                    { !serviceArea.availableFlag && <p>Last Unavailable Timestamp: {formatDateTime(serviceArea.lastUnavailableTimestamp)}</p>}
                    <p>Backup Type: {serviceArea.backupFlag ? "✅" : "❌"}</p>
                    <p>Current Capacity: {serviceArea.currentCapacity}</p>
                    <p>Maximum Capacity: {serviceArea.maximumCapacity}</p>
                </CardContent>
                <RightAlignDiv>
                    <Button text="Toggle availability" type="button" onClickHandler={ () => {toggleAvailability(serviceArea)} }/>
                </RightAlignDiv>
            </CardStyle>

}

export default ServiceAreaCard;