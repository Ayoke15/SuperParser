import {FC} from 'react';
import {Stack, Typography} from "@mui/joy";

import GoHome from "@features/go-home";

const NotFoundPage: FC = () => {
    return (
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
                Такой страницы не существует
            </Typography>
            <GoHome/>
        </Stack>
    );
};

export default NotFoundPage;
