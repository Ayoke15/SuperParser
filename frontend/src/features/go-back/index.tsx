import {FC} from 'react';
import {useNavigate} from "react-router-dom";
import {Button} from "@mui/joy";

const GoBack: FC = () => {
    const navigate = useNavigate();
    const goBack = () => navigate(-1);

    return (
        <Button onClick={goBack}>Вернуться назад</Button>
    );
};

export default GoBack;