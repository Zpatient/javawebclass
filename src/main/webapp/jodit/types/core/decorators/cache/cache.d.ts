/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
export interface CachePropertyDescriptor<T, R> extends PropertyDescriptor {
    get?: (this: T) => R;
}
export declare function cache<T, R>(target: object, name: PropertyKey, descriptor: CachePropertyDescriptor<T, R>): void;