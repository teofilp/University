import axios, { AxiosInstance, AxiosResponse, AxiosRequestConfig } from "axios";

class BaseApi {
  private api: AxiosInstance;

  constructor() {
    this.api = axios.create({
      baseURL: "https://localhost:5001/api",
    });

    this.api.interceptors.request.use((config: AxiosRequestConfig) => {
      const token = localStorage.getItem("access_token");
      config.headers!.Authorization = token ? `Bearer ${token}` : "";

      return config;
    });
  }

  public get<ResponseDataType, Response = AxiosResponse<ResponseDataType>>(
    url: string,
    config?: AxiosRequestConfig
  ): Promise<Response> {
    return this.api.get(url, config);
  }

  public post<
    ResponseDataType,
    BodyType,
    Response = AxiosResponse<ResponseDataType>
  >(
    url: string,
    data?: BodyType,
    config?: AxiosRequestConfig
  ): Promise<Response> {
    return this.api.post(url, data, config);
  }

  public put<
    ResponseDataType,
    BodyType,
    Response = AxiosResponse<ResponseDataType>
  >(
    url: string,
    data?: BodyType,
    config?: AxiosRequestConfig
  ): Promise<Response> {
    return this.api.put(url, data, config);
  }
}

export default new BaseApi();
