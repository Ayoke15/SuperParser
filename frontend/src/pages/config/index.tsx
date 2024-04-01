import axios from "axios";
import {useQuery} from "@tanstack/react-query";
import {Card, CardContent, Stack, Typography} from "@mui/joy";
import {FC} from "react";
import {Link} from "react-router-dom";

const API_URL = "http://95.31.11.209:8888"

export interface ActiveAction {
    id: number,
    xpath_path: string,
    number: number
}


interface ActiveActionCardProps {
    activeAction: ActiveAction,
}

const ActiveActionCard: FC<ActiveActionCardProps> = ({activeAction}) => {
    return (
        <Card
            variant="outlined"
            sx={{
                maxWidth: 350,
            }}
        >
            <CardContent>
                <Typography noWrap>Номер действия: {activeAction.number}</Typography>
                <Typography noWrap>Xpath: {activeAction.xpath_path}</Typography>
            </CardContent>
        </Card>
    );
};


interface ActiveActionListProps {
    activeActions: ActiveAction[],
}

const ActiveActionList: FC<ActiveActionListProps> = ({activeActions}) => {
    return (
        <Stack
            direction="column"
            justifyContent="center"
            alignItems="center"
            spacing={2}
        >
            {activeActions.map(activeAction => (
                <ActiveActionCard key={activeAction.id} activeAction={activeAction}/>
            ))}
        </Stack>
    );
};


export interface Xpath {
    id: number,
    linkSite: string,
    currency: string,
    status: string,
    name: string,
    startDate: string,
    endDate: string,
    publishDate: string,
    company: string,
    link: string,
    startPrice: string,
    code: string,
    nextButton: string,
    username: string,
    password: string,
    loginLink: string,
    loginButton: string,
    activeActions: ActiveAction[]
}

interface XpathCardProps {
    xpath: Xpath,
}

const XpathCard: FC<XpathCardProps> = ({xpath}) => {
    return (
        <Card
            variant="outlined"
            sx={{
                maxWidth: 400,
            }}
        >
            <CardContent>
                <Typography noWrap>Xpath до code: {xpath?.code}</Typography>
                <Typography noWrap>Xpath до name: {xpath?.name}</Typography>
                <Typography noWrap>Xpath до company: {xpath?.company}</Typography>
                <Typography noWrap>Xpath до link: {xpath?.link}</Typography>
                <Typography noWrap>Xpath до currency: {xpath?.currency}</Typography>
                <Typography noWrap>Xpath до endDate: {xpath?.endDate}</Typography>
                <Typography noWrap>Xpath до linkSite: {xpath?.linkSite}</Typography>
                <Typography noWrap>Xpath до loginButton: {xpath?.loginButton}</Typography>
                <Typography noWrap>Xpath до loginLink: {xpath?.loginLink}</Typography>
                <Typography noWrap>Xpath до nextButton: {xpath?.nextButton}</Typography>
                <Typography noWrap>Xpath до password: {xpath?.password}</Typography>
                <Typography noWrap>Xpath до publishDate: {xpath?.publishDate}</Typography>
                <Typography noWrap>Xpath до startDate: {xpath?.startDate}</Typography>
                <Typography noWrap>Xpath до startPrice: {xpath?.startPrice}</Typography>
                <Typography noWrap>Xpath до username: {xpath?.username}</Typography>
                <Typography noWrap>Xpath до status: {xpath?.status}</Typography>
                <ActiveActionList activeActions={xpath?.activeActions}/>
                <Link to={`/config/edit/${xpath.id}`}>Редактировать</Link>
            </CardContent>
        </Card>
    );
};

interface XpathListProps {
    xpaths: Xpath[],
}

const XpathList: FC<XpathListProps> = ({xpaths}) => {
    return (
        <Stack
            direction="column"
            justifyContent="center"
            alignItems="center"
            spacing={2}
        >
            {xpaths.map(xpath => (
                <XpathCard key={xpath.id} xpath={xpath}/>
            ))}
        </Stack>
    );
}

const fetchXpaths = async (): Promise<Xpath[]> => {
    const responseTenders = await axios.get<Xpath[]>(
        `${API_URL}/xpath/all`,
    );

    return responseTenders.data;
}

const ConfigPage = () => {
    const {isLoading, data} = useQuery({
        queryKey: ["xpath's",],
        queryFn: fetchXpaths,
        placeholderData: (previousData) => previousData
    })

    if (isLoading) {
        return (
            <>
                <Typography textAlign="center">Loading...</Typography>
            </>
        );
    }


    return (
        <>
            {data && <XpathList xpaths={data}/>}
        </>
    );
};

export default ConfigPage;