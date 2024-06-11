import styled from "styled-components"

const RightAlignDiv = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
`

const CenteredDiv = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`

const NotDisplayedDiv = styled.div`
    display: none;
`

const RowDiv = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
`

const RowDivTopAligned = styled.div`
    display: flex;
    flex-direction: row;
    align-items: flex-start;
`

export {
    RightAlignDiv,
    CenteredDiv,
    NotDisplayedDiv,
    RowDiv,
    RowDivTopAligned
}

