import styled from "styled-components";

const borderRadius = "20px";

const PackageCardStyle = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    border: 1px solid #bfbfbf;
    border-radius: ${borderRadius};
    width: 25vw;
    padding: 20px 20px 5px 20px;
    box-shadow: 0 4px 8px rgba(0, 0.1, 0.1, 0.1);
    margin: 20px;
    transform: translate(0);
    transition: transform 0.5s ease;
    overflow: hidden;
    &:hover {
        transform: translate(8px, 4px);
        transition: transform 0.5s ease;
        img {
            opacity: 0.7;
        }
    }

`

export { PackageCardStyle }