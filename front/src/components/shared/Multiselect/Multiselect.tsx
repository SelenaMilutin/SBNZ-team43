import { SetStateAction, useState } from "react";
import { Label } from "../Input/Input.style";
import { MultiselectStyle, RemoveSelectedButton, Select, SelectedDiv } from "./Multiselect.style";
import { RowDiv } from "../style/DivStyle";

interface MultiselectProps {
    data: {id: string, name: string}[];
    values: {id: string, name: string}[];
    setSelectedInParent: (val: SetStateAction<{id: string, name: string}[]>) => void;
    isSingleSelect?: boolean;
}

const Multiselect = ({data, values, setSelectedInParent, isSingleSelect = false} : MultiselectProps) => {

    const [selectedValues, setSelectedValues] = useState([...values]);
    const [availableData, setAvailableData] = useState([...data.filter(item => !values.find(value => value.id === item.id))]);

    function handleOnChange(event: any) {

        const selectedId = event.target.value;

        if (isSingleSelect) {
            handleSingleSelectChange(selectedId);
            return;
        }

        let index = availableData.findIndex(d => d.id === selectedId);
        if (index !== -1) 
        {
            setSelectedValues([...selectedValues, availableData[index]])
            setSelectedInParent([...selectedValues, availableData[index]])

            const changed = [...availableData]
            changed.splice(index, 1)
            setAvailableData(changed)
        }
    }

    function handleSingleSelectChange(selectedId: string) {

        if (selectedValues.length > 0) return 

        let index = availableData.findIndex(d => d.id === selectedId);
        if (index !== -1) 
        {
            setSelectedValues([availableData[index]])
            setSelectedInParent([availableData[index]])

            const changed = [...availableData]
            changed.splice(index, 1)
            setAvailableData(changed)
        }
    }

    function removeSelected(id: string) {

        if (isSingleSelect) {
            handleSingleSelectRemoveSelected(id);
            return;
        }

        let index = selectedValues.findIndex(d => d.id === id);
        if (index !== -1) 
        {
            setAvailableData([...availableData, selectedValues[index]])

            const changed = [...selectedValues] 
            changed.splice(index, 1)
            setSelectedValues([...changed])
            setSelectedInParent([...changed])
        }
    }

    function handleSingleSelectRemoveSelected(id: string) {
        let index = selectedValues.findIndex(d => d.id === id);
        if (index !== -1) {
            setAvailableData([...availableData, selectedValues[index]])
            setSelectedValues([])
            setSelectedInParent([])
        }
    }


    return (
        <MultiselectStyle>
            <Select onChange={handleOnChange} defaultValue={undefined}>
                <option></option>
                {availableData.map(option => (
                    <option key={option.id} value={option.id}>
                        {option.name}
                    </option>                
                ))}
            </Select>
            <div>
                <Label>Selected</Label>
                {selectedValues.map(selected => (
                    <SelectedDiv>
                        <RowDiv> 
                            <p key={"option-"+selected.id}> {selected.name}  </p>
                            <RemoveSelectedButton key={selected.id} onClick={(event: any) => {removeSelected(selected.id)}}>x</RemoveSelectedButton>
                        </RowDiv>
                    </SelectedDiv>
                    
                ))}
            </div>
                
        </MultiselectStyle>
    )

}

export default Multiselect;