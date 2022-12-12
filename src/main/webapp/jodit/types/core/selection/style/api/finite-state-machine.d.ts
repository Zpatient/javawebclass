/*!
 * Jodit Editor (https://xdsoft.net/jodit/)
 * Released under MIT see LICENSE.txt in the project root for license information.
 * Copyright (c) 2013-2022 Valeriy Chupurnov. All rights reserved. https://xdsoft.net
 */
import type { CanUndef, IDictionary } from '../../../../types';
/**
 * A state machine implementation for applying styles.
 */
export declare class FiniteStateMachine {
    private state;
    private readonly transitions;
    setState(state: string, subState?: string): void;
    private subState;
    getState(): string;
    getSubState(): string;
    private silent;
    disableSilent(): void;
    constructor(state: string, transitions: IDictionary<IDictionary<(this: FiniteStateMachine, ...attrs: any[]) => any>>);
    dispatch<T>(actionName: string, ...attrs: any[]): CanUndef<T>;
}
