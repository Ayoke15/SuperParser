import {FC} from 'react';
import {useRouteError} from "react-router-dom";
import {Stack, Typography} from "@mui/joy";

import GoBack from "@features/go-back";
import ErrorUI from "@shared/ui/error";


const BasicErrorPage: FC = () => {
    const error = useRouteError();


    return (
        <>
            <Stack
                direction="column"
                justifyContent="center"
                alignItems="center"
                spacing={2}
            >
                <Typography
                    level="h1"
                    textAlign="center"
                    variant="plain"
                >
                    Произошла ошибка
                </Typography>
                <ErrorUI error={error}/>
                <GoBack/>
            </Stack>
        </>
    );
};

export default BasicErrorPage;