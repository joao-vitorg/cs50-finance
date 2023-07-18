/* eslint-disable */
/* tslint:disable */

/*
 * ---------------------------------------------------------------
 * ## THIS FILE WAS GENERATED VIA SWAGGER-TYPESCRIPT-API        ##
 * ##                                                           ##
 * ## AUTHOR: acacode                                           ##
 * ## SOURCE: https://github.com/acacode/swagger-typescript-api ##
 * ---------------------------------------------------------------
 */

export interface ExceptionResponse {
	/** @format date-time */
	timestamp: string;
	message: string;
	datails: string;
}

export interface ClientDto {
	/**
	 * @minLength 3
	 * @maxLength 20
	 * @pattern ^[a-zA-Z0-9_-]+$
	 */
	username: string;
	/**
	 * @minLength 3
	 * @maxLength 2147483647
	 */
	password: string;
}

export interface ClientVo {
	/** @format int64 */
	id: number;
	username: string;
	balance: number;
}

export interface TransactionDto {
	/**
	 * @format int64
	 * @min 1
	 */
	clientId: number;
	/**
	 * @format int64
	 * @min 1
	 */
	stockId: number;
	/** @format int32 */
	shares: number;
}

export interface StockVo {
	/** @format int64 */
	id: number;
	symbol: string;
	name: string;
	price: number;
	/** @format date-time */
	modifiedAt: string;
}

export interface TransactionVo {
	/** @format int64 */
	id: number;
	/** @format int64 */
	clientId: number;
	stock: StockVo;
	/** @format int32 */
	shares: number;
	total: number;
	/** @format date-time */
	createdAt: string;
}

export interface StockDto {
	/**
	 * @minLength 3
	 * @maxLength 10
	 * @pattern ^[a-z0-9]+$
	 */
	symbol: string;
	/**
	 * @minLength 3
	 * @maxLength 100
	 */
	name: string;
	price: number;
}

export interface PageTransactionVo {
	/** @format int32 */
	totalPages?: number;
	/** @format int64 */
	totalElements?: number;
	/** @format int32 */
	size?: number;
	content?: TransactionVo[];
	/** @format int32 */
	number?: number;
	sort?: SortObject;
	pageable?: PageableObject;
	first?: boolean;
	last?: boolean;
	/** @format int32 */
	numberOfElements?: number;
	empty?: boolean;
}

export interface PageableObject {
	/** @format int64 */
	offset?: number;
	sort?: SortObject;
	/** @format int32 */
	pageNumber?: number;
	/** @format int32 */
	pageSize?: number;
	paged?: boolean;
	unpaged?: boolean;
}

export interface SortObject {
	empty?: boolean;
	sorted?: boolean;
	unsorted?: boolean;
}

export interface PageStockVo {
	/** @format int32 */
	totalPages?: number;
	/** @format int64 */
	totalElements?: number;
	/** @format int32 */
	size?: number;
	content?: StockVo[];
	/** @format int32 */
	number?: number;
	sort?: SortObject;
	pageable?: PageableObject;
	first?: boolean;
	last?: boolean;
	/** @format int32 */
	numberOfElements?: number;
	empty?: boolean;
}

export interface PageStockHistoryVo {
	/** @format int32 */
	totalPages?: number;
	/** @format int64 */
	totalElements?: number;
	/** @format int32 */
	size?: number;
	content?: StockHistoryVo[];
	/** @format int32 */
	number?: number;
	sort?: SortObject;
	pageable?: PageableObject;
	first?: boolean;
	last?: boolean;
	/** @format int32 */
	numberOfElements?: number;
	empty?: boolean;
}

export interface StockHistoryVo {
	/** @format int64 */
	id: number;
	/** @format int64 */
	stockId: number;
	price: number;
	/** @format date-time */
	createdAt: string;
}

export interface PageClientVo {
	/** @format int32 */
	totalPages?: number;
	/** @format int64 */
	totalElements?: number;
	/** @format int32 */
	size?: number;
	content?: ClientVo[];
	/** @format int32 */
	number?: number;
	sort?: SortObject;
	pageable?: PageableObject;
	first?: boolean;
	last?: boolean;
	/** @format int32 */
	numberOfElements?: number;
	empty?: boolean;
}

export interface ClientStockVo {
	/** @format int64 */
	id: number;
	/** @format int64 */
	clientId: number;
	stock: StockVo;
	/** @format int32 */
	shares: number;
	total: number;
}

export interface PageClientStockVo {
	/** @format int32 */
	totalPages?: number;
	/** @format int64 */
	totalElements?: number;
	/** @format int32 */
	size?: number;
	content?: ClientStockVo[];
	/** @format int32 */
	number?: number;
	sort?: SortObject;
	pageable?: PageableObject;
	first?: boolean;
	last?: boolean;
	/** @format int32 */
	numberOfElements?: number;
	empty?: boolean;
}

import type { AxiosInstance, AxiosRequestConfig, AxiosResponse, HeadersDefaults, ResponseType } from "axios";
import axios from "axios";

export type QueryParamsType = Record<string | number, any>;

export interface FullRequestParams extends Omit<AxiosRequestConfig, "data" | "params" | "url" | "responseType"> {
	/** set parameter to `true` for call `securityWorker` for this request */
	secure?: boolean;
	/** request path */
	path: string;
	/** content type of request body */
	type?: ContentType;
	/** query params */
	query?: QueryParamsType;
	/** format of response (i.e. response.json() -> format: "json") */
	format?: ResponseType;
	/** request body */
	body?: unknown;
}

export type RequestParams = Omit<FullRequestParams, "body" | "method" | "query" | "path">;

export interface ApiConfig<SecurityDataType = unknown> extends Omit<AxiosRequestConfig, "data" | "cancelToken"> {
	securityWorker?: (
		securityData: SecurityDataType | null
	) => Promise<AxiosRequestConfig | void> | AxiosRequestConfig | void;
	secure?: boolean;
	format?: ResponseType;
}

export enum ContentType {
	Json = "application/json",
	FormData = "multipart/form-data",
	UrlEncoded = "application/x-www-form-urlencoded",
	Text = "text/plain",
}

export class HttpClient<SecurityDataType = unknown> {
	public instance: AxiosInstance;
	private securityData: SecurityDataType | null = null;
	private securityWorker?: ApiConfig<SecurityDataType>["securityWorker"];
	private secure?: boolean;
	private format?: ResponseType;

	constructor({ securityWorker, secure, format, ...axiosConfig }: ApiConfig<SecurityDataType> = {}) {
		this.instance = axios.create({ ...axiosConfig, baseURL: axiosConfig.baseURL || "http://localhost:8080" });
		this.secure = secure;
		this.format = format;
		this.securityWorker = securityWorker;
	}

	public setSecurityData = (data: SecurityDataType | null) => {
		this.securityData = data;
	};

	public request = async <T = any, _E = any>({
																							 secure,
																							 path,
																							 type,
																							 query,
																							 format,
																							 body,
																							 ...params
																						 }: FullRequestParams): Promise<AxiosResponse<T>> => {
		const secureParams =
			((typeof secure === "boolean" ? secure : this.secure) &&
				this.securityWorker &&
				(await this.securityWorker(this.securityData))) ||
			{};
		const requestParams = this.mergeRequestParams(params, secureParams);
		const responseFormat = format || this.format || undefined;

		if (type === ContentType.FormData && body && body !== null && typeof body === "object") {
			body = this.createFormData(body as Record<string, unknown>);
		}

		if (type === ContentType.Text && body && body !== null && typeof body !== "string") {
			body = JSON.stringify(body);
		}

		return this.instance.request({
			...requestParams,
			headers: {
				...(requestParams.headers || {}),
				...(type && type !== ContentType.FormData ? { "Content-Type": type } : {})
			},
			params: query,
			responseType: responseFormat,
			data: body,
			url: path
		});
	};

	protected mergeRequestParams(params1: AxiosRequestConfig, params2?: AxiosRequestConfig): AxiosRequestConfig {
		const method = params1.method || (params2 && params2.method);

		return {
			...this.instance.defaults,
			...params1,
			...(params2 || {}),
			headers: {
				...((method && this.instance.defaults.headers[method.toLowerCase() as keyof HeadersDefaults]) || {}),
				...(params1.headers || {}),
				...((params2 && params2.headers) || {})
			}
		};
	}

	protected stringifyFormItem(formItem: unknown) {
		if (typeof formItem === "object" && formItem !== null) {
			return JSON.stringify(formItem);
		} else {
			return `${formItem}`;
		}
	}

	protected createFormData(input: Record<string, unknown>): FormData {
		return Object.keys(input || {}).reduce((formData, key) => {
			const property = input[key];
			const propertyContent: any[] = property instanceof Array ? property : [property];

			for (const formItem of propertyContent) {
				const isFileType = formItem instanceof Blob || formItem instanceof File;
				formData.append(key, isFileType ? formItem : this.stringifyFormItem(formItem));
			}

			return formData;
		}, new FormData());
	}
}

/**
 * @title Documentação da API CS50 Finance
 * @version Version 1 (v1)
 * @baseUrl http://localhost:8080
 *
 * Esta documentação apresenta os endpoints responsáveis pelas funcionalidades de Stock, User e Transaction.
 */
export class Api<SecurityDataType extends unknown> extends HttpClient<SecurityDataType> {
	client = {
		/**
		 * No description
		 *
		 * @tags Client
		 * @name FindClientById
		 * @request GET:/api/v1/client/{id}
		 */
		findClientById: (id: number, params: RequestParams = {}) =>
			this.request<ClientVo, ExceptionResponse>({
				path: `/api/v1/client/${id}`,
				method: "GET",
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Client
		 * @name UpdateClient
		 * @request PUT:/api/v1/client/{id}
		 */
		updateClient: (id: number, data: ClientDto, params: RequestParams = {}) =>
			this.request<ClientVo, ExceptionResponse>({
				path: `/api/v1/client/${id}`,
				method: "PUT",
				body: data,
				type: ContentType.Json,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Client
		 * @name FindAllClients
		 * @request GET:/api/v1/client
		 */
		findAllClients: (
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageClientVo, ExceptionResponse>({
				path: `/api/v1/client`,
				method: "GET",
				query: query,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Client
		 * @name InsertClient
		 * @request POST:/api/v1/client
		 */
		insertClient: (data: ClientDto, params: RequestParams = {}) =>
			this.request<ClientVo, ExceptionResponse>({
				path: `/api/v1/client`,
				method: "POST",
				body: data,
				type: ContentType.Json,
				...params
			})
	};
	transaction = {
		/**
		 * No description
		 *
		 * @tags Transaction
		 * @name FindAllTransactions
		 * @request GET:/api/v1/transaction
		 */
		findAllTransactions: (
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageTransactionVo, ExceptionResponse>({
				path: `/api/v1/transaction`,
				method: "GET",
				query: query,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Transaction
		 * @name InsertTransaction
		 * @request POST:/api/v1/transaction
		 */
		insertTransaction: (data: TransactionDto, params: RequestParams = {}) =>
			this.request<TransactionVo, ExceptionResponse>({
				path: `/api/v1/transaction`,
				method: "POST",
				body: data,
				type: ContentType.Json,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Transaction
		 * @name FindTransactionByClientId
		 * @request GET:/api/v1/transaction/client/{id}
		 */
		findTransactionByClientId: (
			id: number,
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageTransactionVo, ExceptionResponse>({
				path: `/api/v1/transaction/client/${id}`,
				method: "GET",
				query: query,
				...params
			})
	};
	stock = {
		/**
		 * No description
		 *
		 * @tags Stock
		 * @name FindAllStocks
		 * @request GET:/api/v1/stock
		 */
		findAllStocks: (
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageStockVo, ExceptionResponse>({
				path: `/api/v1/stock`,
				method: "GET",
				query: query,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Stock
		 * @name InsertStock
		 * @request POST:/api/v1/stock
		 */
		insertStock: (data: StockDto, params: RequestParams = {}) =>
			this.request<StockVo, ExceptionResponse>({
				path: `/api/v1/stock`,
				method: "POST",
				body: data,
				type: ContentType.Json,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Stock
		 * @name FindStockById
		 * @request GET:/api/v1/stock/{id}
		 */
		findStockById: (id: number, params: RequestParams = {}) =>
			this.request<StockVo, ExceptionResponse>({
				path: `/api/v1/stock/${id}`,
				method: "GET",
				...params
			})
	};
	stockHistory = {
		/**
		 * No description
		 *
		 * @tags Stock History
		 * @name FindStockHistoryByStockId
		 * @request GET:/api/v1/stock/{id}/history
		 */
		findStockHistoryByStockId: (
			id: number,
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageStockHistoryVo, ExceptionResponse>({
				path: `/api/v1/stock/${id}/history`,
				method: "GET",
				query: query,
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Stock History
		 * @name FindAllStockHistory
		 * @request GET:/api/v1/stock/history
		 */
		findAllStockHistory: (
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageStockHistoryVo, ExceptionResponse>({
				path: `/api/v1/stock/history`,
				method: "GET",
				query: query,
				...params
			})
	};
	clientStock = {
		/**
		 * No description
		 *
		 * @tags Client Stock
		 * @name GetVirtualBalance
		 * @request GET:/api/v1/client/{id}/virtualBalance
		 */
		getVirtualBalance: (id: number, params: RequestParams = {}) =>
			this.request<number, ExceptionResponse>({
				path: `/api/v1/client/${id}/virtualBalance`,
				method: "GET",
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Client Stock
		 * @name FindClientStockByClientId
		 * @request GET:/api/v1/client/{id}/stock
		 */
		findClientStockByClientId: (id: number, params: RequestParams = {}) =>
			this.request<PageClientStockVo, ExceptionResponse>({
				path: `/api/v1/client/${id}/stock`,
				method: "GET",
				...params
			}),

		/**
		 * No description
		 *
		 * @tags Client Stock
		 * @name FindAllClientStocks
		 * @request GET:/api/v1/client/stock
		 */
		findAllClientStocks: (
			query?: {
				/**
				 * Zero-based page index (0..N)
				 * @min 0
				 * @default 0
				 */
				page?: number;
				/**
				 * The size of the page to be returned
				 * @min 1
				 * @default 20
				 */
				size?: number;
				/** Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. */
				sort?: string[];
			},
			params: RequestParams = {}
		) =>
			this.request<PageClientStockVo, ExceptionResponse>({
				path: `/api/v1/client/stock`,
				method: "GET",
				query: query,
				...params
			})
	};
}
