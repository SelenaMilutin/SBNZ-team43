import { useEffect, useState } from "react";
import { ServiceArea } from "../../models/servicearea/ServiceArea";
import { ServiceAreaService } from "../../services/ServiceAreaService";
import { ServiceAreaPageStyle } from "./ServiceAreaPageStyle";
import ServiceAreaCardList from "../../components/servicearea/ServiceAreaCardList";

const ServiceAreaPage = () => {

    const [serviceAreas, setServiceAreas] = useState<ServiceArea[]>([]);

    useEffect( () => {
        getServiceAreas()
    }, [])

    async function getServiceAreas() {
        try {
            const res = await ServiceAreaService.getAll()
            
            setServiceAreas(res.data.map((area: ServiceArea) => ({
                ...area,
                lastUnavailableTimestamp: new Date(area.lastUnavailableTimestamp),
              })))
        }
        catch {
            alert("Couldn't load service areas. Try again later.")
        }
    }

    async function toggleAvailability(serviceArea: ServiceArea) {
        console.log(serviceArea)
        console.log(serviceAreas)
        try {
            const response = await ServiceAreaService.toggleAvailability(serviceArea.id, !serviceArea.availableFlag);
            console.log(response);

            console.log(serviceAreas)
            const index = serviceAreas.findIndex(d => d.id === serviceArea.id);
            console.log(index);
            if (index !== -1) {
                const updatedServiceAreas = [...serviceAreas];
                let res = response.data;
                console.log(res)
                res.lastUnavailableTimestamp = new Date(response.data.lastUnavailableTimestamp);
                updatedServiceAreas[index] = res;
                console.log(updatedServiceAreas)
                setServiceAreas(updatedServiceAreas)
            }
        } catch (error) {
            console.log(error);
            // alert("Couldn't toggle availability. Try again later.");
        }
    }

    return <ServiceAreaPageStyle>
        <h1>Service areas</h1>
        <ServiceAreaCardList
            serviceAreas={serviceAreas}
            toggleAvailability={toggleAvailability}
        />
    </ServiceAreaPageStyle>

}

export default ServiceAreaPage;