const proxyPath = () => {
    return (window.PROXY_PATH === '/') ? '' : window.PROXY_PATH;
};
const contextPath = () => {
    return (window.CONTEXT_PATH === '{CONTEXT_PATH}') ? '' : window.CONTEXT_PATH;
};
const apiBaseUrl = (oriUrl) => {
    const baseUrl = (window.API_BASE_URL === '{API_BASE_URL}') ? '/' : window.API_BASE_URL;
    return (baseUrl + oriUrl).replace('//', '/');
};
const adminBaseUrl = (oriUrl) => {
    const baseUrl = (window.ADMIN_BASE_URL === '{ADMIN_BASE_URL}') ? '/' : window.ADMIN_BASE_URL;
    return (baseUrl + oriUrl).replace('//', '/');
};

const defaultOptionData = {
    resultStructure: true,
    responseFormat:
        '{\n' +
        '  "success"      : "@resultStatus",\n' +
        '  "message"      : "@resultMessage",\n' +
        '  "code"         : "@resultCode",\n' +
        '  "lifeCycleTime": "@timeLifeCycle",\n' +
        '  "executionTime": "@timeExecution",\n' +
        '  "value"        : "@resultData"\n' +
        '}',
    wrapAllParameters: false,
    wrapParameterName: 'root',
};

// 通用查 配置
const ApiUrl = {
    checkVersion: 'http://apis.hasor.net/projects/hasor-dataway/checkVersion',
    //
    apiInfo: proxyPath() + contextPath() + adminBaseUrl(`/api/api-info`),
    apiList: proxyPath() + contextPath() + adminBaseUrl(`/api/api-list`),
    apiDetail: proxyPath() + contextPath() + adminBaseUrl(`/api/api-detail`),
    apiHistory: proxyPath() + contextPath() + adminBaseUrl(`/api/api-history`),
    apiHistoryInfo: proxyPath() + contextPath() + adminBaseUrl(`/api/get-history`),
    //
    execute: proxyPath() + contextPath() + apiBaseUrl(`/`),
    apiSave: proxyPath() + contextPath() + adminBaseUrl(`/api/save-api`),
    perform: proxyPath() + contextPath() + adminBaseUrl(`/api/perform`),
    smokeTest: proxyPath() + contextPath() + adminBaseUrl(`/api/smoke`),
    publish: proxyPath() + contextPath() + adminBaseUrl(`/api/publish`),
    disable: proxyPath() + contextPath() + adminBaseUrl(`/api/disable`),
    deleteApi: proxyPath() + contextPath() + adminBaseUrl(`/api/delete`),
    //
    analyzeSchema: proxyPath() + contextPath() + adminBaseUrl(`/api/analyze-schema`),
};

export {
    ApiUrl, defaultOptionData, apiBaseUrl, contextPath
};
