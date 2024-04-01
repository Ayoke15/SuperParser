import {FC} from 'react';
import {Form, redirect, useParams} from "react-router-dom";
import axios from "axios";
import {Xpath} from "@pages/config";
import {useQuery} from "@tanstack/react-query";
import {Button, Card, FormControl, FormLabel, Input, Stack} from "@mui/joy";

const API_URL = "http://localhost:8888"

interface EditXpathCard {
    xpath: Xpath
}






const EditXpathCard: FC<EditXpathCard> = ({xpath}) => {
    return (

        <Card>
            <form>
                <FormControl>
                    {Object.keys(xpath).filter(key => !["activeActions", "id"].includes(key)).map(key => (
                        <div key={key} style={{marginBottom: 15}}>
                            <FormLabel>{key}</FormLabel>
                            <Input name={key} value={xpath[key]}/>
                        </div>
                    ))}
                    <Button type="submit">Сохранить</Button>
                </FormControl>
            </Form>
        </Card>
    );
};


const fetchXpath = async (id: number): Promise<Xpath> => {
    const responseTenders = await axios.get<Xpath>(
        `${API_URL}/xpath/get-by-id?id=${id}`,
    );

    return responseTenders.data;
}


const EditConfigPage: FC = () => {
    const {id} = useParams();
    const {isLoading, data} = useQuery({
        queryKey: ["xpath", id],
        queryFn: () => fetchXpath(id)
    })

    return (
        <>
            {data && <EditXpathCard xpath={data}/>}
        </>
    );
};

export default EditConfigPage;