/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
/**
 * @module modules/file-browser
 */
import type { HTMLTagNames, IFileBrowserItem, Nullable, IDictionary, IFileBrowser } from '../../../types';
export declare const getItem: (node: Nullable<EventTarget>, root: HTMLElement, tag?: HTMLTagNames) => Nullable<HTMLElement>;
export declare const elementToItem: (elm: HTMLElement, elementsMap: IDictionary<{
    elm: HTMLElement;
    item: IFileBrowserItem;
}, string>) => IFileBrowserItem | void;
export declare function nativeListeners(this: IFileBrowser): void;