import {FC} from 'react';
import {AspectRatio, Sheet, Stack, Typography} from "@mui/joy";
import {NavLink} from "react-router-dom";


const HeaderUI: FC = () => {
    return (
        <header>
            <Sheet>
                <Stack
                    direction="row"
                    justifyContent="flex-start"
                    alignItems="center"
                    spacing={6}
                    sx={{px: 8, py: 2}}
                >
                    <Typography
                        noWrap
                        level="h2"
                    >
                        <NavLink to={"/"}>Tender</NavLink>
                    </Typography>
                    <Typography
                        noWrap
                    >
                        Find your best tender
                    </Typography>
                    <NavLink to={"/config"}>config</NavLink>
                </Stack>
                <AspectRatio
                    minHeight={120}
                    maxHeight={400}
                >
                    <img
                        src="https://i.ibb.co/WgNpRGb/header-background.png"
                        alt="header-background"
                    />
                    <Stack
                        direction="column"
                        justifyContent="center"
                        alignItems="center"
                        position="absolute"
                        sx={{
                            width: "100%",
                            height: "100%",
                        }}
                        spacing={4}
                    >
                        <Typography
                            level="h1"
                            textAlign="center"
                            sx={{color: "white", fontSize: 60, maxWidth: 600}}
                        >
                            Поиск тендеров
                        </Typography>
                        <Typography
                            fontSize={20}
                            textAlign="center"
                            sx={{color: "white", maxWidth: 480}}
                        >
                            Предоставляем рекомендационный сервис для поиска приоретизации тендеров
                        </Typography>
                    </Stack>
                </AspectRatio>
            </Sheet>
        </header>
    );
};

export default HeaderUI;