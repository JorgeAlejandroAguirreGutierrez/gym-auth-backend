package co.com.bancolombia.api.constants;

public class RouterPaths {

    public static final String CONSULT_METRICS_BY_NAME = "/api/consult-metrics-by-name/{index}";
    public static final String CONSULT_METRICS_FILTER = "/api/consult-metrics-filter";
    public static final String CREATE_METRIC = "/api/create-metric";
    public static final String UPDATE_METRIC = "/api/update-metric";
    public static final String DELETE_METRIC = "/api/delete-metric";
    public static final String GET_AZURE_TOKEN = "/api/get-azure-token";


    //APIs METRICS FINANCES
    public static final String CONSULT_API_FINANCE_METRIC_FOM_DB = "/api/metric-finance/consultmetricfromdb";
    public static final String CREATE_API_FINANCE_METRIC = "/api/metric-finance/createmetric";
    public static final String CONSULT_API_FINANCE_PROJECT_FOM_DB = "/api/metric-finance/consultprojectfromdb";
    public static final String CREATE_API_FINANCE_PROJECT = "/api/metric-finance/createproject";
    public static final String CONSULT_API_FINANCE_PROJECT_DETAIL_FOM_DB = "/api/metric-finance/consultprojectdetailfromdb";
    public static final String CREATE_API_FINANCE_PROJECT_DETAIL = "/api/metric-finance/createprojectdetail";
    public static final String CONSULT_API_FINANCE_MEET_CHANNEL = "/api/metric-finance/consultmeetchannel";
    public static final String CREATE_API_FINANCE_MEET_CHANNEL = "/api/metric-finance/createmeetchannel";
    public static final String CONSULT_API_FINANCE_MEET_CHANNEL_EVENT = "/api/metric-finance/consultmeetchannelevent";
    public static final String CREATE_API_FINANCE_MEET_CHANNEL_EVENT = "/api/metric-finance/createmeetchannelevent";
    public static final String API_FINANCE_ALL_MIGRATION_DB_TO_OPENSEARCH = "/api/metric-finance/migrardbtoopensource";

}
