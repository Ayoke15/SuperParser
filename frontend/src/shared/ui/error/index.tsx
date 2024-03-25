import {FC} from "react";
import {isRouteErrorResponse} from "react-router-dom";
import {List, ListItem, Stack, Typography} from "@mui/joy";

interface ErrorProps {
    error: unknown

}

const ErrorUI: FC<ErrorProps> = ({error}) => {

    if (isRouteErrorResponse(error)) {
        return (
            <Typography>
                Информация об ошибке: {error.statusText}
            </Typography>
        );

    } else if (error instanceof Response) {
        return (
            <Stack>
                <Typography>
                    Информация об ошибке:
                </Typography>
                <List marker="disc">
                    <ListItem>Сообщение: {error.statusText}</ListItem>
                    <ListItem> Стутс: {error.status}</ListItem>
                </List>
            </Stack>
        );
    } else if (typeof error == "string") {
        return (
            <Typography>
                Информация об ошибке: {error}
            </Typography>
        );
    }

    return (
        <Typography>
            Неизвестная ошибка
        </Typography>
    );
};

export default ErrorUI;