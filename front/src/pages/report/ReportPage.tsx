import { useEffect, useState } from "react";
import { ServiceAreaPageStyle } from "../servicearea/ServiceAreaPageStyle";
import { ContractService } from "../../services/ContractService";

const ReportPage = () => {

    const [reports, setReports] = useState<{indexLabel: string, y: number}[]>([]);

    useEffect(() => {
        GetReport()
    }, [])

    async function GetReport() {
        let res = await ContractService.getReport();
        setReports(res.data)
    }

    return <ServiceAreaPageStyle>
        <h2>Report for proportion of sold prepaid and postpaid packages in contracts</h2>
        <p>Number of prepaid contracts: {reports.at(0)?.y}</p>
        <p>Number of postpaid contracts: {reports.at(1)?.y}</p>
    </ServiceAreaPageStyle>
}

export default ReportPage;