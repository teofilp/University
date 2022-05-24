import {
  View,
  StyleSheet,
  Text,
  Document,
  Page,
  BlobProvider,
} from "@react-pdf/renderer";
import { Button } from "antd";
import "./main.css";

interface PdfTableProps {
  columns: {
    dataIndex: string;
    title: string;
    render?: (value: any) => any;
  }[];
  dataSource: any[];
  title: string;
  orientation?: "portrait" | "landscape";
  size?: number;
  show?: boolean;
}

const defaultRenderMethod = (value: any) => value;

const getRenderMethod = (column: any) => column.render ?? defaultRenderMethod;

const getStyles = (columns: any, size: number) =>
  StyleSheet.create({
    page: {
      flexDirection: "column",
      padding: 32,
    },
    tableContainer: {
      flexDirection: "column",
      flexWrap: "wrap",
    },
    tableHeader: {
      display: "flex",
      flexGrow: 1,
      flexDirection: "row",
      padding: "12px 24px",
      backgroundColor: "#00152a",
      justifyContent: "center",
    },
    tableHeaderCell: {
      color: "white",
      display: "flex",
      justifyContent: "center",
      width: `${100 / columns.length || 1}%`,
      textAlign: "center",
      fontSize: size * 1.1,
    },
    cell: {
      display: "flex",
      justifyContent: "center",
      width: `${100 / columns.length || 1}%`,
      fontSize: size,
      textAlign: "center",
    },
    tableRow: {
      display: "flex",
      flexGrow: 1,
      flexDirection: "row",
      padding: "12px 24px",
      borderBottom: "1px solid #efefef",
      alignItems: "center",
    },
    tableTitle: {
      fontSize: size * 1.5,
      fontWeight: "bold",
      margin: "16px 0",
      display: "flex",
    },
  });

export const PdfTable = ({
  columns,
  dataSource,
  title,
  orientation,
  size = 14,
  show = true,
}: PdfTableProps) => {
  const styles = getStyles(columns, size);

  const TableHeader = () => (
    <View style={styles.tableHeader}>
      {columns.map((col) => (
        <Text style={styles.tableHeaderCell}>{col.title}</Text>
      ))}
    </View>
  );

  const TableContent = () => (
    <>
      {dataSource.map((x) => (
        <View style={styles.tableRow}>
          {columns.map((col) => (
            <Text style={styles.cell}>
              {getRenderMethod(col)(x[col.dataIndex])}
            </Text>
          ))}
        </View>
      ))}
    </>
  );

  const Table = (
    <Document>
      <Page wrap={false} orientation={orientation} style={styles.page}>
        <Text style={styles.tableTitle}>{title}</Text>
        <View style={styles.tableContainer}>
          <TableHeader />
          <TableContent />
        </View>
      </Page>
    </Document>
  );

  return (
    <div className="table-wrapper">
      <BlobProvider document={Table}>
        {({ url }) => {
          return (
            <div className="download-wrapper">
              <Button
                type="primary"
                size="large"
                href={url ?? ""}
                target="_blank"
              >
                Download
              </Button>
            </div>
          );
        }}
      </BlobProvider>
      {show ? Table : null}
    </div>
  );
};
