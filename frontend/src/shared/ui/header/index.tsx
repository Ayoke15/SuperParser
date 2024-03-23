import {FC} from 'react';
import {AspectRatio, Sheet, Stack, Typography} from "@mui/joy";


const HeaderUI: FC = () => {
    return (
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
                    Tender
                </Typography>
                <Typography
                    noWrap
                >
                    Find your best tender
                </Typography>
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
                        Какая то дичь про тендеры
                    </Typography>
                    <Typography
                        fontSize={20}
                        textAlign="center"
                        sx={{color: "white", maxWidth: 480}}
                    >
                        Что то про нашу копец прикольную еализацию мф же солодцы такие я прям в шоке от того какие мы
                        умные
                    </Typography>
                </Stack>
            </AspectRatio>
        </Sheet>
    );
};

export default HeaderUI;