/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module modules/file-browser
 */
import type { IDictionary, IFileBrowserItem, IViewBased } from '../../../types';
declare type ElementsMap = IDictionary<{
    elm: HTMLElement;
    item: IFileBrowserItem;
}>;
/**
 * Returns a map of the file's key correspondence to its DOM element in the file browser
 */
export declare const elementsMap: (view: IViewBased) => ElementsMap;
export {};
