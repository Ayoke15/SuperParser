import axios from "axios";

import {FC, useState} from 'react';
import {Button, Card, CardContent, FormControl, FormLabel, Input, Skeleton, Stack, Typography} from "@mui/joy";
import {Pagination, PaginationItem} from "@mui/material";
import {NavLink, useLocation} from "react-router-dom";
import {useQuery} from "@tanstack/react-query";


const API_URL = "http://95.31.11.209:8080/api/get-all-tenders?"

interface Tender {
    code: string,
    currency: string,
    status: {
        id: number,
        name: string
    },
    name: string
    startDate?: string,
    endDate: string,
    publishDate: string,
    company: string,
    link?: string,
    startPrice?: string
}

interface TenderCardProps {
    tender: Tender
}

const TenderCard: FC<TenderCardProps> = ({tender}) => {
    return (
        <Card
            variant="outlined"
            sx={{
                maxWidth: 400,
            }}
        >
            <CardContent>
                <Typography level="title-md">{tender.name}</Typography>
                <Typography>Код: {tender.code}</Typography>
                <Typography>Валюта: {tender.currency}</Typography>
                <Typography>Статус: {tender.status.name}</Typography>
                <Typography>Дата старта продаж: {tender.startDate}</Typography>
                <Typography>Дата окончания продаж: {tender.endDate}</Typography>
                <Typography>Дата публикаци: {tender.publishDate}</Typography>
                <Typography>Компания: {tender.company}</Typography>
                <Typography>
                    <a href={tender.link} target="_blank">полная информация</a>
                </Typography>
                <Typography>{tender.startPrice}</Typography>
            </CardContent>
        </Card>
    );
};

interface TenderListProps {
    tenders: Tender[]
}

const TenderList: FC<TenderListProps> = ({tenders}) => {
    return (
        <Stack
            direction="column"
            justifyContent="center"
            alignItems="center"
            spacing={2}
        >
            {tenders.map(tender => (
                <TenderCard key={tender.code} tender={tender}/>
            ))}
        </Stack>
    );
};


const TenderCardLoader: FC = () => {
    return (
        <Card
            variant="outlined"
            sx={{
                maxWidth: 400,
            }}
        >
            <CardContent>
                <Typography level="title-md"><Skeleton>Поставка перил ограждения (502/12-02; согл-3029858-1;
                    496/Исх-АРСУ)</Skeleton></Typography>
                <Typography><Skeleton>Код: ТН-180411-147</Skeleton></Typography>
                <Typography><Skeleton>Валюта: RUB</Skeleton></Typography>
                <Typography><Skeleton>Статус: Торги приостановлены</Skeleton></Typography>
                <Typography><Skeleton>Дата старта продаж: 2018-04-26 15:00:00</Skeleton></Typography>
                <Typography><Skeleton>Дата окончания продаж: 2018-04-26 15:00:00</Skeleton></Typography>
                <Typography><Skeleton>Дата публикаци: 2018-04-16 00:00:00</Skeleton></Typography>
                <Typography><Skeleton>Компания: ООО "ТаграС-Нефтегазстрой"</Skeleton></Typography>
                <Typography>
                    <Skeleton>полная информация</Skeleton>
                </Typography>
                <Typography><Skeleton></Skeleton></Typography>
            </CardContent>
        </Card>
    );
};


const ButtonLoading: FC = () => {
    const [loading, setLoading] = useState(false);

    const click = () => {
        setLoading(true);
        setTimeout(() => setLoading(false), 15000)
    }

    return (
        <Button onClick={click} loading={loading}>
            Начать парсинг
        </Button>
    );
};


interface FetchTendersData {
    tenders: Tender[],
    totalPagesCount: number
}


const fetchTenders = async (from: number, size: number): Promise<FetchTendersData> => {
    const responseTenders = await axios.get<Tender[]>(
        `${API_URL}from=${from}&size=${size}`,
        {
            headers: {
                Accept: "*/*"
            }
        }
    )

    return {
        tenders: responseTenders.data,
        totalPagesCount: Number(responseTenders.headers["totalpagescount"])
    } as FetchTendersData
}

const HomePage: FC = () => {
    const location = useLocation();
    const params = new URLSearchParams(location.search);

    const [from, setFrom] = useState(parseInt(params.get("from") || "0"));
    const [size, setSize] = useState(parseInt(params.get("size") || "10"));


    const {data, error, isFetching} = useQuery({
        queryKey: ["tenders", {from, size}],
        queryFn: () => fetchTenders(from, size),
        placeholderData: (previousData) => previousData,
    })


    if (error) {
        throw error;
    }


    return (
        <>
            <Stack
                direction="column"
                justifyContent="center"
                alignItems="center"
                spacing={2}
            >
                <Stack
                    direction="row"
                    justifyContent="center"
                    alignItems="self-end"
                    spacing={2}
                >
                    <ButtonLoading/>
                    <FormControl>
                        <FormLabel>Кол-во тендеров на странице</FormLabel>
                        <Input
                            type="number"
                            defaultValue={size}
                            onChange={event => (
                                setSize(event.target.valueAsNumber)
                            )}
                        />
                    </FormControl>

                    <Pagination
                        showFirstButton
                        showLastButton
                        count={data?.totalPagesCount}
                        page={(from / size) + 1}
                        variant="outlined"
                        onChange={(_, page) => {
                            setFrom(size * (page - 1))
                        }}
                        renderItem={(item) => (
                            <PaginationItem
                                component={NavLink}
                                to={`/?from=${item.page ? size * (item.page - 1) : 0}&size=${size}`}
                                {...item}
                            />
                        )}
                    />
                </Stack>

                {isFetching ? (
                    <Stack
                        direction="column"
                        justifyContent="center"
                        alignItems="center"
                        spacing={2}
                    >
                        {Array(size).fill(undefined).map((_, index) => (
                            <TenderCardLoader key={index}/>
                        ))}
                    </Stack>
                ) : (
                    data?.tenders && <TenderList tenders={data.tenders}/>
                )}

            </Stack>
        </>
    );
};

export default HomePage;