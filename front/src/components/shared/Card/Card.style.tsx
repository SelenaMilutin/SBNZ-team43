import styled from "styled-components";

const borderRadius = "20px";

const CardStyle = styled.div`
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

const CardTitle = styled.h3`
    margin: 0;
    font-size: 2em;
    text-shadow: 2px 2px 5px #ffffff;
`

const CardContent = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 80%;
    margin-bottom: 20px;
`

const CardDetails = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    padding-right: 20px;
`

const CardImgOverlay = styled.img`
    width: 100%;
    height: 100%;
    object-fit: cover; 
    position: absolute; 
    top: 0; 
    left: 0;
    border-radius: ${borderRadius};
    opacity: 0.5;
    z-index: -1;
    transition: opacity 0.3s ease;
`

const CardImgStyle = styled.img`
    width: 50%;
    height: auto;
    object-fit: cover;
    border-radius:${borderRadius};
`

const CardSubtitle = styled.span`
    margin: 0;
    font-size: 1.2em;
    font-style: italic;
    font-weight: 500;
`

export { 
    CardStyle, 
    CardTitle, 
    CardSubtitle,
    CardContent,
    CardImgOverlay,
    CardImgStyle,
    CardDetails
};
