import {FC} from 'react';
import RouterProvider from "@app/providers/router-provider";
import QueryClientProvider from "@app/providers/query-client-provider";

const Providers: FC = () => {
    return (
        <>
            <QueryClientProvider>
                <RouterProvider/>
            </QueryClientProvider>
        </>
    );
};

export default Providers;