import {Table} from 'antd';

const {Column} = Table;

export const CurriculumTable = ({data}: { data: any }) => {
    return (
        <Table dataSource={data} pagination={false} className="table-content">
            <Column title="NO.CRT" dataIndex="crt" key="crt"/>
            <Column title="Name" dataIndex="name" key="name"/>
        </Table>
    );
}