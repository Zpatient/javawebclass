/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module helpers/utils
 */
import type { IViewComponent } from '../../../types';
export declare const dataBind: <T = any>(elm: IViewComponent | Node | object, key: string, value?: T | undefined) => T;
