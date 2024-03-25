import {FC} from 'react';
import {Button} from "@mui/joy";
import {useNavigate} from "react-router-dom";

const GoHome: FC = () => {
    const navigate = useNavigate();
    const goHome = () => navigate("/")

    return (
        <Button onClick={goHome}>Вернуться на главную</Button>
    );
};

export default GoHome;